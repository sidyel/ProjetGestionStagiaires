# projet de gestion des stagiaire
La plateforme est une solution centralisée pour le suivi et l'encadrement des stagiaires en entreprise. Elle permet de suivre les tâches et projets, gérer les utilisateurs et les rôles, faciliter la communication interne et assurer le suivi des absences, le tout via une architecture microservices et une interface Angular.

Objectifs:
Rationaliser le suivi des stagiaires, faciliter un mentorat efficace, améliorer la gestion des tâches et générer des attestation de fin de stage.
Solution proposée:
Une application centralisée composée de microservices pour assurer modularité, indépendance, scalabilité et maintenabilité, avec un frontend Angular responsive pour l'expérience utilisateur.

Architecture générale:
Architecture orientée microservices avec découverte de services via Eureka et communication inter-services via Feign. Chaque service possède sa base de données PostgreSQL dédiée et utilise Spring Boot et Spring Data JPA pour la persistance. L'authentification est assurée par des JWT. Un API Gateway est recommandé pour centraliser le routage et l'authentification.

Microservices existants et responsabilité:
client-service gère les utilisateurs, l'authentification et les rôles. suivi-service gère les projets, tâches, documents et affectations. message-absence gère la messagerie interne et le suivi des absences. frontend-angular est l'interface utilisateur en Angular. Un service additionnel report-service .
