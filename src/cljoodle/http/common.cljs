(ns cljoodle.http.common
  (:require [env.moodle.config :as moodle-config]))

(def rest-server-endpoint (str "/webservice/rest/server.php"
                               "?" moodle-config/moodle-json-wrap-response-url-param))

(def rest-server-url
  (str moodle-config/url-and-port
       rest-server-endpoint))
