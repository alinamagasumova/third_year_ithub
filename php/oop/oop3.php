<?php
class Lamp{
    const BRAND = "Phillips";
    public static $count = 0;

    function getBrand(){
        return  self::BRAND;
    }
    function __construct(){
        ++self::$count;
    }
    static function getCount(){
        return  self::$count;
    }
}
echo Lamp::BRAND, "\n";
echo (new Lamp())->getBrand(), "\n";

$lamp = new Lamp();
$lamp2 = new Lamp();
$lamp3 = new Lamp();
echo Lamp::getCount(), "\n";

class A{
    static function whoAmI(){
        echo __CLASS__ . "\n";
    }
    static function identity(){
        // self::whoAmI();
        static::whoAmI();
    }
}
class B extends A{
    static function whoAmI(){
        echo __CLASS__ . "\n";
    }
}
A::identity(); // A
B::identity(); // A //// B

function foo(){
    static $x = 0;
    echo $x . "\n";
    $x++;
}
foo(); //0
foo(); //1
foo(); //2