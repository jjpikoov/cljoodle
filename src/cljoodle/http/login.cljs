(ns cljoodle.http.login
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn login-to-moodle
  "Allows to login to moodle with basic auth"
  [login password]
  (go (let [response (<! (try (http/post "http://example.com" {:form-params {:key1 [1 2 3] :key2 "value2"}}) 
                              (catch :default e
                                (println e))))]
        (prn   response))))
