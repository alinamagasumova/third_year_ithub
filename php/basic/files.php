<?php
// useful funcs
// check files and dirs
if(file_exists('data.txt'))
    echo "OK\n";
if(is_file('data.txt'))
    echo "This is file\n";
if(is_dir('data.txt'))
    echo "This is directory\n";
// size of a file
echo filesize('data.txt') . "\n";
echo filemtime('data.txt') . "\n";
// what can we do
if(is_readable('data.txt'))
    echo 'Can read' . "\n";
if(is_writeable('data.txt'))
    echo 'Can write' . "\n";



$f = fopen('data.txt', 'r') or die('Error');
echo fread($f, 5);
echo fread($f, 3);
fpassthru($f);
fclose($f);

$f = fopen('data.txt', 'r') or die('Error');
echo fread($f, 1024);
fclose($f);

// read file by lines to an array
$f = fopen('data.txt', 'r') or die('Error');
$lines = [];
while($line = fgets($f)) $lines[] = $line;
fclose($f);
print_r($lines);
// read file by bytes to an array
$f = fopen('data.txt', 'r') or die('Error');
$bytes = [];
while(!feof($f)) $bytes[] = fgetc($f);
fclose($f);
print_r($bytes);

// write into file
// $f = fopen('data.txt', 'a') or die('Error');
// fwrite($f, "\nLine six"); // fputs
// fputs($f, "\nThis is very very really long line", 13);
// fclose($f);

$f = fopen('data.txt', 'r') or die('Error');
fseek($f, -10, SEEK_END);
echo ftell($f);
echo fread($f, 10);
fclose($f);



// read file to buffer
// $f = fopen('data.txt', 'r');
// fpassthru($f);
// fclose($f);
// ==
readfile('data.txt');

// read file by lines to an array
// $f = fopen('data.txt', 'r');
// $lines = [];
// while($line = fgets($f)) $lines[] = $line;
// fclose($f);
// ==
$lines = file('data.txt');

// whole file in a line
// $f = fopen('data.txt', 'r');
// $content = fread($f, filesize('data.txt'));
// fclose($f);
// ==
$content = file_get_contents('data.txt');

// record to file
// $f = fopen('data.txt', 'w');
// fwrite($f, "New content"); // fputs
// fclose($f);
// ==
file_put_contents('data.txt', 'New content');
// $f = fopen('data.txt', 'a');
// fwrite($f, "New content"); // fputs
// fclose($f);
// ==
file_put_contents('data.txt', 'New content', FILE_APPEND);



// file management
copy('source', 'destination');
rename('old', 'new');
unlink('filename'); // delete file