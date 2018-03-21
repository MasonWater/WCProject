wc.exe -c 1111.c

wc.exe -l 1111.c

wc.exe -w 1111.c -e stoplist.txt

wc.exe -a 1111.c

wc.exe -l -a -w 1111.c

wc.exe -l -a -c 1111.c -o output.txt 

wc.exe -l -c -w 1111.c -o output.txt

wc.exe -s -c -w *.c 

wc.exe -s -c -w d:/TESTC/*.c

wc.exe -s -a -c -w *.c -e stoplist.txt -o output.txt