(ns cljoodle.converter.events-converter)


(defn convert-event-to-menu-item-format
  [on-press-function event]
  {:name        (:name event)
   :description (:description event)
   :timestart   (.toString (js/Date. (* (:timestart event) 1000)))
   :on-press    (on-press-function (:id event))})
