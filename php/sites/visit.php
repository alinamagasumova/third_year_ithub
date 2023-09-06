<?php
$counter = 0;
if(isset($_COOKIE['visitCounter'])){
    $counter = $_COOKIE['visitCounter'];
}
$counter++;
if(isset($_COOKIE['lastVisit'])){
    $visit = date('d-m-y H:i:s', $_COOKIE['lastVisit']);
}
setcookie('visitCounter', $counter, 0x7FFFFFFF);
setcookie('lastVisit', time(), 0x7FFFFFFF);
?>
<h1>Visit counter</h1>
<?php
if($counter==1){
    echo "<p>Welcome</p>";
} else{
    echo "<p>You entered $counter times</p>";
    echo "<p>Last visit was: $visit</p>";
}
?>
