<?php
// declare(strict_types=1);
echo 'Hello, world', "!\n";
print 'Helo, world\n';

$a = 42;
$b = $a + 10;
echo "Result: $b\n";

$apple =  'apple';
echo "Many {$apple}s\n";

echo gettype(5); // integer
echo gettype(5.2); // double (float)

echo is_bool(true); // 1

echo "hello" . " " . "world"; // concat strs using .

echo (int) 1.5; // 1
echo (int) false; // 0
echo (int) true; // 1
echo (int) "abc"; // 0
echo (int) "10abc"; // 10
echo (int) ""; // 0
echo (int) "0"; // 0

echo 2 + "2"; // 4
// echo 2 + "abc"; // warning
// echo 10 + "10abc"; // warning 20

const AAA = 100;
echo "... {AAA} ..." . AAA;

if(true){
    //
}else{
    //
}

$x = 2;
switch($x){
    case 1: echo '...'; break;
    case "2": echo '!!!' . "\n"; break;
    default: echo '???';
}

while(false){
    while(false){
        break 2;
        continue 2;
    }
};
for(;false;);
// do while(false);

// && || most high priority
// and  or really low priority
// $x = foo() or exit();
// $x = foo() || exit;

// exit;
// exit();
// exit('bye');
// die;
// die();
// die('bye');

function foo($a, $b=2){
    return $a + $b;
}
foo(2, 3);
$fn = 'foo';
echo $fn(2, 3) . "\n";

function bar(){
    function baz(){

    }
}
bar(); // after this baz will be a global fn
baz();

$c = 1;
// $GLOBALS['c'] = 1;
function useGlobal() {
    global $c;
    $GLOBALS['c'];
}

function links($a, &$b){
    echo $a + $b;
    $b = 5; //changed a global variable
    echo $a + $b;
}
links(1, $c);

function sum_positive($a, $b, &$c){
    $result = $a + $b;
    if($result >= 0) $c = true;
    else $c = false;
    return $result;
}
$sum = sum_positive(2, 3, $positive);

// error_reporting(0); - to turn off error notifications (despite deprecated and parse ones)
// в продакшне отключать все ошибки обязательно
// error_reporting(E_ALL); - to turn on 
// проверить после того как проект собран
// error_reporting(E_ERRROR|E_WARNING); - to turn off error notifications (despite deprecated, parse, fatak and warnings)
// в разработке работаем как хотим
// E_PARSE, E_ERROR, E_WARNING, E_DEPRECATED, E_NOTICE

// error_reporting(0);
// function my_error_handler($no, $str, $file, $line){
//     if($no == E_WARNING or $no == E_USER_WARNING){
//         echo "something has happened";
//     }
//     echo "\n" . "HERE: " . $str . "\n";
//     $msg = "ERROR: $str in $file on line $line\n";
//     error_log($msg, 3, "error.log");
// }
// set_error_handler("my_error_handler");
// const A = 1;
// const A = 2;
// $a  = 'a';
// if($a == 'a') trigger_error('That is my error', E_USER_WARNING);

$a = [10=>1, 2, 2=>3, 4, 7=>5];
$a[15] = 6;
$a["2"] = 7;
// print_r($a);

$b = ['name'=>'John', 'age'=>25, 'pass'=>1234, 'admin'=>true];
// print_r($b);
// var_dump($b);
// echo $b['name'];
// foreach($b as $k=>$v){
//     echo "$k: $v\n";
// }

$nums = [1, 2, 3, 4, 5];
foreach($nums as &$v){
    $v *= 10;

}
print_r($nums);
$v = 100;
print_r($nums);

function fooo(){
    return [1, 2, 3];
}

// list($x, $y, $z) = fooo();
[$x, $y, $z] = fooo();
[$a, $b] = [$b, $a];
echo $x, $y, $z;

echo fooo()[2];

