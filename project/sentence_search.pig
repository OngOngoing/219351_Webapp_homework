lines = LOAD 'GTB' AS line;
user_input = LOAD '$user_input' AS (user_sentence:chararray);
books = FOREACH lines GENERATE FLATTEN(REGEX_EXTRACT_ALL(line,'(.*)\\{\\_\\}(.*)\\{\\_\\}(.*)')) AS (name, sentence_id, sentence);
joined = JOIN user_input BY user_sentence, books BY sentence;
result = FOREACH joined GENERATE name, sentence_id, sentence;

store result into '$output_path';
