(defn robot [a-name attack hp] (fn [message] (message a-name attack hp)))

(def robot-vasya (robot "Vasya" 25 200))

(defn a-name [n _ _] n)
(defn attack [_ attack _] attack)
(defn hp [_ _ hp] hp)

(defn get-name [a-robot] (a-robot a-name))
; get-name [fn [message] (message "Vasya" 25 200)]
; fn [(fn [n _ _] n)] (message "Vasya" 25 200)
; ((fn [n _ _] n) "Vasya" 25 200)
(defn get-attack [a-robot] (a-robot attack))
(defn get-hp [a-robot] (a-robot hp))

(get-name robot-vasya)
(get-attack robot-vasya)
(get-hp robot-vasya)

(defn set-name [a-robot new-name] (a-robot (fn [n a h] (robot new-name a h))))
(defn set-attack [a-robot new-attack] (a-robot (fn [n a h] (robot n new-attack h))))
(defn set-hp [a-robot new-hp] (a-robot (fn [n a h] (robot n a new-hp))))

(def robot-ivan (set-name robot-vasya "Ivan"))
(def robot-vasya-2 (set-attack robot-vasya 5))
(def robot-vasya-3 (set-hp robot-vasya 50))

(defn pribt-robot [a-robot] (a-robot #(str %1 " attack: " %2 " hp: " %3)))
(pribt-robot robot-vasya)
(pribt-robot robot-ivan)

(defn damage [a-robot attack-damage] 
  (a-robot #(robot %1 %2 (- %3 attack-damage))))
;;   (a-robot (fn [n a h] (robot n a (- h attack-damage)))))

(defn fight [r1 r2] ())