<?php
abstract class Lamp{
    public $model;
    public $status;

    function __construct($model){
        if(!$model) throw new Exception('Write model!');
        $this->model = $model;
        $this->status = false;
    }
    abstract function switchOn();
    abstract function switchOff();
}
class TableLamp extends Lamp{
    function switchOn(){
        echo "Turned on\n";
        $this->status = true;
    }
    function switchOff(){
        echo "Turned off\n";
        $this->status = false;
    }
}
$lamp = new TableLamp('Phillips');

interface Shape{
    function draw();
}
class Rectangle implements Shape{
    function draw(){}
}
// you can't extend this class (because final) can be also used for functions
final class Breakfast{
    function eatFood($food){ echo "Ate $food"; }
}
class User{
    private $login;
    private $pass;
    private $params;
    function __construct($l, $p){
        $this->login = $l;
        $this->pass = $p;
        $this->params = $this->getParams();
    }
    private function getParams(){ return [1,2,3]; }
    function __sleep(){
        return ['login', 'pass'];
    }
    function __wakeup(){
        $this->params = $this->getParams();
    }
}
$user = new User('john', '1234');
$str = serialize($user);
unset($user);
$user = unserialize($str);