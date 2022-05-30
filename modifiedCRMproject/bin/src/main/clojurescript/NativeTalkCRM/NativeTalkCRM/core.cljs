(ns NativeTalkCRM.NativeTalkCRM.core)

(defn reverso [halb]
  (.join (.reverse (.split halb "")) ""))
