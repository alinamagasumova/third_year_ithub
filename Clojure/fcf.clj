;; (defn if-even-inc [n] (if (even? n) (inc n) n))
;; (defn if-even-double [n] (if (even? n) (* n 2) n))
;; (defn if-even-square [n] (if (even? n) (* n n) n))

(defn if-even [func n] (if (even? n) (func n) n))

(defn incr [n] (+ n 1))
(defn dbl [n] (* n 2))
(defn sqr [n] (* n n))

(defn if-even-inc [n] (if-even incr n))
(defn if-even-double [n] (if-even dbl n))
(defn if-even-square [n] (if-even sqr n))

(def user '("Alina" "Maga"))
;; (first user) -Alina (second user) -Maga

(def users ['("John" "Smith")
             '("Philip" "Nerd")
             '("David" "Fun")
             '("Huan" "Nerd")
             '("Alex" "Fun")])

(defn cmp-ln [n1 n2] (compare (second n1) (second n2)))
(defn cmp-fn [n1 n2] (compare (first n1) (first n2)))

(defn compare-last-name [name1 name2] (if (= (cmp-ln name1 name2) 0)
                                        (cmp-fn name1 name2) (cmp-ln name1 name2)))
