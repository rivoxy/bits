# Project Base for Vaadin and Spring Boot

Callable REST webservices :

root URL on localhost with the embedded tomcat server : http://localhost:8080/kataservices

create a new match instance : 
http://localhost:8080/kataservices/rs/rmatch/creategame/{p1}/{p2}

findall :
http://localhost:8080/kataservices/rs/rmatch/findall
http://localhost:8080/kataservices/rs/sets/findall
http://localhost:8080/kataservices/rs/gs/findall

register player point : 
http://localhost:8080/kataservices/rs/gs/addplayerpoint/{matchid}/{playername}

update deuce an tie break rules : deuce Y/N and tiebreak Y/N by default those values are set to N
http://localhost:8080/kataservices/rs/rmatch/updaterules/{matchid}/{deuce}/{tiebreak}

removeall :
http://localhost:8080/kataservices/rs/rmatch/removeall
