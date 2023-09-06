<?php
function my_autoloader($name){
    require "$name.class.php";
}
spl_autoload_register('my_autoloader');
$myClass = new MyClass();