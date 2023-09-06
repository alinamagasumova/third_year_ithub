<pre>
<?php
// print_r($_GET); // request.QueryString
// echo "Your name: " . $_GET['name'] . "<br>";
// echo "Your age: " . $_GET['age'] . "<br>";

print_r($_POST); // request.Form
$name = trim(strip_tags($_POST['name']));
$age = abs((int)$_POST['age']);
if($name and $age) {
    echo "Your name: " . $name . "<br>";
    echo "Your age: " . $age . "<br>";
}
// header('Location: post.php');
header('Refresh: 3;url=post.php');
// print_r($_REQUEST);
// print_r($_SERVER);