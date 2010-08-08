(ns com.abedra.hello.Hello
  (:gen-class
   :extends android.app.Activity
   :exposes-methods {onCreate superOnCreate}))

(defn -onCreate [this #^android.os.Bundle bundle]
  (.superOnCreate this bundle)
  (.setContentView this com.abedra.hello.R$layout/main)
  (let [tv (android.widget.TextView. this)]
    (.setText tv "Hello Android From Clojure")
    (.setContentView this tv)))
