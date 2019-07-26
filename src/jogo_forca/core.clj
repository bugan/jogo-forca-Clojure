(ns jogo-forca.core
  (:gen-class))


(defn ganhou [] (println "Voce ganhou :)"))
(defn perdeu [] (println "Voce perdeu :("))
(defn tem-vida? [vidas] (> vidas 0))
(defn espera-input! [] (read-line))

(defn letras-faltantes [palavra acertos]
  (remove (fn [letra] (contains? acertos (str letra))) palavra))

(defn acertou-a-palavra? [palavra acertos]
  (empty? (letras-faltantes palavra acertos)))

(defn existe-letra? [palavra letra]
  (.contains palavra letra))

(defn jogo [palavra vidas acertos]
  (if (tem-vida? vidas)    
    (if (acertou-a-palavra? palavra acertos) 
      (ganhou)
      (do 
        (let [chute (espera-input!)] 
          (if (existe-letra? palavra chute)
            (do
              (println "letra correta")
              (println (conj acertos chute))
              (recur palavra vidas (conj acertos chute))
            )
            (do
              (println "letra errada")
              (recur palavra (dec vidas) acertos))))
            )
    ) (perdeu)))

(defn inicia-jogo [palavra vidas]
  (jogo palavra vidas #{}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))