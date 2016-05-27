#Pig Usage

##Parameters
* user_input
* output_path

##Execution
* Script name : `sentence_search.pig`

* Execute the pig sentence search script (Local).
```sh
pig -x local -param user_input="input.txt" -param output_path=output_search -f sentence_search.pig
```

Where `input.txt` is the input file (generated depends on request).

* Execute the pig sentence search script (Map-Reduce)
```sh
pig -param user_input="input.txt" -param output_path=output_search -f sentence_search.pig
```

Where `input.txt` is the input file (generated depends on request).

##Input

* Input Format
```sh
input_sentence1
input_sentence2
...
```

* Example Input
```
The Project Gutenberg EBook of The Bab Ballads, by W. S. Gilbert (#3 in our series by W. S. Gilbert)  Copyright laws are changing all over the world.
Be sure to check the copyright laws for your country before downloading or redistributing this or any other Project Gutenberg eBook.
This header should be the first thing seen when viewing this Project Gutenberg file.
```

##Output
* Output Format
```sh
bookname sentence_id sentence
```

* part of Example Output
```sh
brnte10	2	This header should be the first thing seen when viewing this Project Gutenberg file.
morem10	1	Be sure to check the copyright laws for your country before downloading or redistributing this or any other Project Gutenberg eBook.
```

##Searching Pool
* Uses sentence breaker library from `nltk.corpus.Gutenberg` and generate modified version book files
* Folder name(Searching pool) : `GTB`(See in script)
* modified version of book files have the following structure
```sh
bookname{_}sentence_id{_}sentence
bookname{_}sentence_id{_}sentence
...
```
