<?php
session_start();
$name = '';
$age = '';
if(isset($_SESSION['name'])) {
    $name = $_SESSION['name'];
}
if(isset($_SESSION['age'])) {
    $age = $_SESSION['age'];
}
echo $name, ": ", $age;