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
