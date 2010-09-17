(ns webrepl.core
  (:use [clojure.main :only [main]])
  (:import javax.swing.JApplet)
  (:gen-class :extends javax.swing.JApplet))

(defn make-console [applet]
  (let [console (bsh.util.JConsole.)]
    (.add applet console)
    (binding [*out* (java.io.OutputStreamWriter. (.getOut console))
              *in*  (clojure.lang.LineNumberingPushbackReader. (.getIn console))
              *err* (.getOut console)]
      (.start (Thread. (bound-fn [] (main)))))))

(defn -init [this]
  (make-console this))