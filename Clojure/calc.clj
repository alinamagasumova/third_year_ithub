(defn calc-change
  [owed given]
  (if (> (- given owed) 0)
      (- given owed)
    0))

(defn calc-change'
  [owed given]
  (let [change (- given owed)]
    (if(> change 0) change 0)))

(defn if-test flag
  (if flag 
    (do (println 'Hello')
        (println 'Hello 2'))
    (do (println 'X')
        (println 'Y'))))

(defn when-test flag
  (when flag (println 'Hello')))

(defn positive-number [n]
  (if-let [num (> n 0)]
    num
    "False"))