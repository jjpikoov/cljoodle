;;; File declares converting function for events
(ns cljoodle.converter.events-converter)

(defn- epoch-time-to-string
  "Uses js functions/methods to convert epoch time to string"
  [epoch-time]
  (.toString (js/Date. (* epoch-time 1000))))

(defn date-to-epoch-int
  "Uses js functions/methods to convert date to epoch time"
  [year month day]
  (/ (.getTime (js/Date. year, (dec month), day)) 1000))

(defn convert-event-to-menu-item-format
  "Converts event"
  [event]
  {:name        (:name event)
   :description (:description event)
   :timestart   (epoch-time-to-string (:timestart event))
   :id          (:id event)})
