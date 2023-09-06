(defn cup [ml] (fn [message] (message ml))) ;; #(% ml)
(def small-cup (cup 180)) 
(def big-cup (cup 500)) ;; #(% 500)

(defn get-ml [a-cup] (a-cup (fn [ml] ml))) ;; #(%)
(get-ml big-cup) ;; #(#(%) 500)

(defn drink [a-cup ml-drank] (let [ml-diff (- (get-ml a-cup) ml-drank)]
                               (if (>= ml-diff 0) (cup ml-diff) (cup 0))))

(def after-a-sip (drink big-cup 30))
(get-ml after-a-sip) ;; 470
(def after-two-sips (drink after-a-sip 30))
(get-ml after-two-sips) ;; 440
(def after-gulp (drink after-two-sips 120))
(get-ml after-gulp) ;; 320
(def after-super-gulp (drink after-gulp 400))
(get-ml after-super-gulp) ;; 0

(defn cup-empty? [a-cup] (= (get-ml a-cup) 0))
(cup-empty? after-super-gulp) ;; true
(cup-empty? small-cup) ;; false


(def after-many-sips (reduce drink small-cup [30 30 30 30]))
(get-ml after-many-sips)