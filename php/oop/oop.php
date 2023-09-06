<?php
class Lamp{
    private $_status;
    private $_floor;
    function __construct($floor=1){
        $this->_status=false;
        $this->_floor=$floor;
        echo "Created a lamp on {$this->_floor} floor\n";
    }
    function __destruct(){
        echo "Lamp on {$this->_floor} floor was deleted\n";
    }
    public function switchOn(){
        echo "Turned on\n";
        $this->_status = true;
    }
    public function switchOff(){
        echo "Turned off\n";
        $this->_status = false;
    }
    function __get($name){
        switch($name){
            case 'status': return $this->_status;break;
            case 'floor': return $this->_floor;break;
        }
    }
    function __set($name, $value){
        switch($name){
            case 'status': echo "You are not allowed to change the status\n"; break;
            case 'floor': $this->_floor = $value; break;
        }
    }
    function __call($name, $args){} // if no method is found
    static function __callStatic($name, $args){} // if no static method is found
    function __clone(){ echo "Lamp from {$this->_floor} floor was cloned"; }
    // function functionName(){ echo __FUNCTION__, "\n";}
    // function className(){ echo __CLASS__, "\n";}
    // function methodName(){ echo __METHOD__, "\n";}
}
// echo __LINE__, "\n";
// echo __FILE__, "\n";
// echo __DIR__, "\n";
// function foo() { echo __FUNCTION__, "\n"; }
// foo();
$lamp = new Lamp();
$lamp2 = new Lamp(2);
// $lamp3 = new Lamp(3);
echo $lamp->switchOn();
echo $lamp->_status;
echo $lamp->switchOff();
echo $lamp->_status;
echo $lamp->_status = true;
// echo $lamp->functionName();
// echo $lamp->className();
// echo $lamp->methodName();
// echo gettype($lamp), "\n";
// echo is_object($lamp), "\n";
// echo $lamp instanceof Lamp, "\n";
$lamp2 = clone $lamp;
$lamp2->floor = 2;
echo $lamp->floor;