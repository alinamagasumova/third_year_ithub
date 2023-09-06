<?php
$user = ['name'=>'John', 'login'=>'root', 'password'=>'1234'];
$str = base64_encode(serialize($user));
$user = null;
echo $str . "<br>";
$user = unserialize(base64_decode($str));
print_r($user);