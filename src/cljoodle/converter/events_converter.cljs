(ns cljoodle.converter.events-converter)


(defn _epoch_time_to_string
  [epoch-time]
  (.toString (js/Date. (* epoch-time 1000))))

(defn date_to_epoch_int
  [year month day]
  (prn (str year
            ""
            month
            ""
            day))
  (/ (.getTime (js/Date. year, (dec month), day)) 1000))

(defn convert-event-to-menu-item-format
  [on-press-function event]
  {:name        (:name event)
   :description (:description event)
   :timestart   (_epoch_time_to_string (:timestart event))
   :id          (:id event)
   :on-press    (on-press-function (:id event))})


