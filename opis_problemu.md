# Projekt IO


## Opis dziedziny problemu

Głosowania i referenda występują w wielu aspektach życia publicznego. Niemniej jednak trudno jest znaleźć obiektywny, niezależny i stabilny system umożliwiający przeprowadzanie różnego rodzaju głosowań. Trudno znaleźć system dobrze spełniający jednocześnie potrzeby zarówno ankietowanych jak i ankietujących.

Potrzebny jest więc system umożliwiający głosowanie osobom z określonych grup w wybranych ankietach (z uwzględnieniem uprawnień), a jednocześnie uniemożliwiać wpływanie na wynik poprzez wielokrotne głosowanie przez pojedynczego użytkownika. Spełnienie takich wymagań mogłoby umożliwić zastosowanie systemu w wielu dziedzinach - lokalne referenda, głosowania na budżet obywatelski, badania socjologiczne, itd.

## Zarys rozwiązania

Projektowany system powinien przede wszystkim zapewniać system uprawnień dla użytkowników, by mogli głosować tylko w wyznaczonych ankietach. Adminstracja systemu powinna mieć możliwość grupowego generowania jednorazowych kont dla użytkowników, możliwych do użycia w określonych ankietach. System nie powinien dawać wglądu w głosy oddane przez poszczególne konta. Każde głosowanie powinno mieć określoną ramę czasową, w której można oddawać głosy. Po zakończeniu głosowania powinien zostać sporządzony raport widoczny dla wybranych kont użytkowników i administracji.

## Specyfikacja rozwiązania

Aplikacja do głosowania będzie się składać z następujących modułów:
1.	Moduł profile użytkownika
2.	Moduł do zarządzania profilami użytkownika
3.	Moduł do wprowadzania i konfigurowania ankiet
4.	Moduł do ankietyzacji

Moduł profile użytkownika będzie odpowiadał za zarządzanie profilem przez samego użytkownika. W ramach tego modułu użytkownik będzie mógł:
1.	Założyć profil (rejestracja)
2.	Zalogować się do profilu (dostęp)
3.	Uzupełnić profil (wprowadzenie danych)
Moduł do zarządzania profilami będzie przeznaczony do zarządzania wewnętrznego, przeglądania zawartości profili oraz ich ewentualnego moderowania dzięki następującym funkcjonalnościom:
1.	Przeglądanie profili użytkownika
2.	Modyfikowanie profili użytkownika
3.	Ręczne założenie konta użytkownika
4.	Zarządzanie uprawnieniami użytkownika
Moduł do wprowadzania i konfigurowania ankiet będzie współzależny z profilami użytkownika. Umożliwi on tworzenie głosowań oraz konkretyzację ichv pytań i odpowiedzi. Dodatkowo moduł powinien być poszerzony o dodatkowe statystyki bieżące, związane ze stanem głosowania i sposobem oddawania głosów przez publiczność. Ankiety będą mogły funkcjonować w dwóch trybach - ankiet anonimowych (wymagany jest token aby przystąpić do ankiety) oraz ankiety ograniczonych profilem użytkownika. Dodatkowo niektóre ankiety będą mogły wymagać odpowiedniego zaproszenia przed wypełnieniem. Ich funkcjonalnościami będą:
1.	Wygenerowanie głosowania oraz określenie pytań i odpowiedzi
2.	Wygenerowanie oraz pogląd statystyki związanej z głosowaniem
3.	Definicja docelowej populacji (tryb anonimowy, konto, wymagane zaproszenie)
Moduł do ankietyzacji:
Moduł dla klienta końcowego, który będzie w stanie przystąpić do ankiety. W zależności od rodzaju ankiety sposób do dołączenia do niej będzie miał różną formę. Wyposażony zostanie w statystyki aktualizowane na bierząco, dotyczące przebiegającej ankiety. Moduł będzie połączony z profilem użytkownika aby miał wgląd w ankiety, w których bezpośrednio uczestniczył. Moduł będzie realizował następujące zadania:
1.	Przystępowanie do ankiet
2.	Wypełnienie oraz oddawnie ankiet
3.	Podgląd statystyk związanych z oddanymi głosami
4.	Podgląd przez użytkownika w jakich ankietach brał udział

## Pytania do klienta

1. Jakiego rzędu wielkości grupa będzie korzystała z aplikacji?
2. Do jakiego typu użytkowników będzie skierowana?
3. Czy ankiety powinny zawierać możliwość wielokrotnego wyboru?
4. Jaka ilość administratorów jest przewidziana?
5. Na jakie urządzenia powinna być przede wszystkim zoptymalizowana aplikacja?

