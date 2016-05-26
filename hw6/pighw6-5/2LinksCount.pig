links1 = LOAD 'web-Google.txt' USING PigStorage('\t') AS (from:chararray, to:chararray);
links2 = FOREACH links1 GENERATE from AS from2, to AS to2;
joined = JOIN links1 BY to, links2 BY from2;
links_no_middle = FOREACH joined GENERATE from, to2;
unioned = UNION links1, links_no_middle;

groups = GROUP unioned BY from;
result = FOREACH groups GENERATE group, unioned.$1;

store result into 'output2';
