<?php
class NumberSquared implements Iterator{
    private $start, $end, $cur;
    function __construct($start, $end){
        $this->start = $start;
        $this->end = $end;
    }
    public function rewind(): void{
        $this->cur = $this->start;
    }
    #[\ReturnTypeWillChange]
    public function valid(){
        return $this->cur <= $this->end;
    }
    #[\ReturnTypeWillChange]
    public function current(){
        return pow($this->cur, 2);
    }
    #[\ReturnTypeWillChange]
    public function key(){
        return $this->cur;
    }
    #[\ReturnTypeWillChange]
    public function next(){
        $this->cur++;
    }
}
$obj = new NumberSquared(3, 7);
foreach($obj as $key=>$val){
    echo "Square of $key = $val\n";
}