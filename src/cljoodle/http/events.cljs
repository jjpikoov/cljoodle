(ns cljoodle.http.events
  (:require [cljoodle.http.util.requests :as requests]
            [cljoodle.http.common :as http-comm]))


(defn get-events
  [handler token course-id]
  (requests/do-post-request #(-> %
                                 :body
                                 :events
                                 handler)
                            http-comm/rest-server-url
                            {:wstoken                         token
                             (keyword "events[courseids][0]") course-id
                             :wsfunction                      "core_calendar_get_calendar_events"}))

(defn remove-event
  [handler token event-id]
  (requests/do-post-request #(-> % handler)
                            http-comm/rest-server-url
                            {:wstoken                       token
                             (keyword "events[0][eventid]") event-id
                             (keyword "events[0][repeat]")  1
                             :wsfunction                    "core_calendar_delete_calendar_events"}))


(defn add-event
  [handler token course-id data]
  (requests/do-post-request #(-> % handler)
                            http-comm/rest-server-url
                            {:wstoken                           token
                             (keyword "events[0][courseid]")    course-id
                             (keyword "events[0][name]")        (:name data)
                             (keyword "events[0][format]")      2
                             (keyword "events[0][description]") (:description data)
                             (keyword "events[0][timestart]")   (:timestart data)
                             :wsfunction                        "core_calendar_create_calendar_events"}))
