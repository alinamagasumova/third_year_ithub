<?php
class Point{
    private $x;
    private $y;
    function __construct($x=0, $y=0){
        $this->x = $x;
        $this->y = $y;
    }
    function moveTo($x, $y){
        $this->x = $x;
        $this->y = $y;
    }
    function __toString(){
        return "I am the point {$this->x}x{$this->y}";
    }
}
$point = new Point(10, 20);
echo $point;
class Point3D extends Point{
    private $z;
    function __construct($x, $y, $z){
        $this->z = $z;
        parent::__construct($x, $y);
    }
    function __toString(){
        return parent::__toString() . "x{$this->z}\n";
    }
}
$point3d = new Point3D(10, 20, 30);
echo $point3d;

function test($var=false){
    try{
        echo "START\n";
        if(!$var) throw new Exception('$var is false!');
        echo "CONTINUE\n";
    }catch(Exception $e){
        echo $e->getMessage() . "\n";
        echo $e->getFile() . "\n";
        echo $e->getLine() . "\n";
    }finally{
        echo "ALWAYS HERE\n";
    }
    echo "THE END\n";
}
// var_dump(test(), test(true));
class MathException extends Exception{
    function __construct($msg){
        parent::__construct($msg);
    }
}
class ZeroException extends Exception{
    function __construct($msg){
        parent::__construct($msg);
    }
}
try{
    $x = rand(5, 15);
    $y = rand(0, 1);
    if($y == 0) throw new ZeroException('На ноль делить нельзя');
}catch(ZeroException $e){
    
}catch(MathException $e){

}catch(Exception $e){

}