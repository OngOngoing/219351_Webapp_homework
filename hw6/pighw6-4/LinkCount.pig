lines = LOAD 'web-Google.txt' USING PigStorage('\t') AS (link1:chararray, link2:chararray);
groups = GROUP lines BY link2;
link2count = FOREACH groups GENERATE group, COUNT(lines);
store link2count into 'linkcount';
