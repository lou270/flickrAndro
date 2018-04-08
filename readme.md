## Application FlickrAndro
* Affichage de l'image et de son nom récupérer depuis Flickr
* Choix du tags à rechercher par l'utilisateur

Deux classes dérivant d'AsyncTask : 
* **DownloadJSONFlickr** : recupère le JSON de Flickr et lance autant d'instance de DownloadImage qu'il y a d'image répétoriée dans le JSON
* **DownloadImage** :  Télécharge une image et l'ajoute à l'objet MyImage
La ListView se charge ensuite de l'affichage des données

Autres classes : 
* **MyImage** : contient les données de chaque image
* **ImageAdapter** : permet la mise en forme dans la ListView (hérite de **ArrayAdapter** pour ne pas à avoir à ré-implémenter toutes les méthodes de base)

**Louis Barbier et Joël Nguesson**