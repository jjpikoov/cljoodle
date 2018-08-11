(ns env.moodle.config)

(def login-endpoint "/login/token.php?moodlewsrestformat=json")

(def wstoken "91f1cab7104f608a0d06fe4f34cd6e4e")

(def url-and-port "http://10.0.3.2:80")

(def moodle-base-url
  "It's simply url with port followed by some stuff to achieve json response"
  (str url-and-port "/webservice/rest/server.php?moodlewsrestformat=json"))


