(ns cljoodle.android.components.events.events-add-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]))

(defn _generate-picker-items
  [key-prefix range-start range-end-excl]
  (map (fn [i]
         [rw/picker-item {:key   (str key-prefix i)
                          :label (str i)
                          :value (str i)}])
       (range range-start range-end-excl)))

(defn _generate-days-picker-items
  []
  (_generate-picker-items "day" 1 31))

(defn _generate-month-picker-items
  []
  (_generate-picker-items "month" 1 13))

(defn _generate-year-picker-items
  []
  (_generate-picker-items "year" 2018 (+ 2018 50)))

(defn events-add-component
  []
  (let [day (subscribe [:get-event-new-day])
        month (subscribe [:get-event-new-month])
        year (subscribe [:get-event-new-year])]
    [rw/view (nav/navigator-component "Add event")
     [rw/view {:style {:align-items "center"}}
      [rw/scroll-view
       ; event name
       [rw/text-input {:style       {:width         200
                                     :margin-bottom 30}
                       :placeholder "Event name"
                       :on-change-text
                                    #(dispatch [:set-event-new-name %])}]
       ; day
       [rw/text "Day"]
       [rw/picker {:style           {}
                   :selected-value  @day
                   :mode            "dropdown"
                   :on-value-change #(dispatch [:set-event-new-day %])}
        (_generate-days-picker-items)]

       ; month
       [rw/text {:style {:padding-top 20}} "Month"]
       [rw/picker {:style           {}
                   :selected-value  @month
                   :mode            "dropdown"
                   :on-value-change #(dispatch [:set-event-new-month %])}
        (_generate-month-picker-items)]

       ; year
       [rw/text {:style {:padding-top 20}} "Year"]
       [rw/picker {:style           {}
                   :selected-value  @year
                   :mode            "dropdown"
                   :on-value-change #(dispatch [:set-event-new-year %])}
        (_generate-year-picker-items)]

       ; description
       [rw/text-input {:style        {:width         200
                                      :margin-bottom 30}
                       :placeholder  "Description"
                       :multiline    true
                       :onChangeText #(prn "foo")}]

       ; submit
       [rw/touchable-highlight {:style    {:background-color "#a50e9b"
                                           :margin-bottom    5}
                                :on-press #(prn "submit")}
        [rw/text {:style {:color       "white"
                          :text-align  "center"
                          :padding     15
                          :font-weight "bold"}} "+"]]

       ]]]))
