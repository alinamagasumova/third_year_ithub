(defn address-letter' [a-name location] (let [full-name (str (first a-name) " " (second a-name))]
                                       (str full-name " - " location)))
;; a/я 1234, Санкт-Питерcбург, 941112
;; a/я 456, Нижний Новгород, 895234
;; a/я 789, Москва, 100133
;; (adress-letter' '("Alex" "Fun") a/я 1234, Санкт-Питерcбург, 941112)

(defn ms-office [a-name] (let [full-name (str (first a-name) " " (second a-name))]
                           (str full-name ": a/я 789, Москва, 100133")))

(defn nn-office [a-name] (str (second a-name) " - a/я 456, Нижний Новгород, 895234"))

(defn vl-office [a-name] (let [full-name (str (first a-name) " " (second a-name))] (str "Многоуважаемый(ая) " full-name " - a/я 955, Владивосток, 672904")))

(defn sp-office [a-name] (let [last-name (second a-name) 
                               full-name (str (first a-name) " " last-name)] 
                           (if (-> last-name (compare "L") (< 0)) 
                        ;; (if  (< (compare last-name "L") 0) 
                           (str full-name " - a/я 1234, Санкт-Питерcбург, 941112")
                           (str full-name " - a/я 567, Санкт-Питерcбург, 234567"))))

(defn get-location [location] 
  (case location 
    "ms" ms-office 
    "sp" sp-office 
    "nn" nn-office
    "vl" vl-office
    (fn [a-name] (str (first a-name) " " (second a-name)))))

(defn address-letter [a-name location] 
  (let [func (get-location location)]
    (func a-name)))
(defn address-letter-curr [location a-name] (address-letter a-name location))

(defn flip [binary-func] (fn [x y] (binary-func y x)))
(def address-letter-3 (flip address-letter))
(def address-letter-sp (partial address-letter-3 "sp"))

;;;;;;;;;
(defn test-cond [n] 
  (cond 
    (= n 1) "this is 1"
    (and (> n 3) (< n 10)) "n greater than 3 and less than 10"
    :else "n ???"))
