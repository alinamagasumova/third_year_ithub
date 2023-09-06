(defn to-part [recipient] (str "Уважаемый(ая) " recipient "!\n"))
(defn body-part [title] (str "Спасибо, что посетили \"" title "\"\n"))
(defn from-part [author] (str "С уважением, " author))

(defn create-email 
 [recipient title author]
 (str (to-part recipient) (body-part title) (from-part author)))

(println "Кто получатель этого письма?")
(def recipient (read-line))
(println "Названиие курса: ")
(def title (read-line))
(println "От кого?")
(def author (read-line))

(println (create-email recipient title author))