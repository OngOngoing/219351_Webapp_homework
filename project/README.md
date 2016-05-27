#How To Use

##Parameters
* user_input
* output_path

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
