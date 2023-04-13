// Declare variables
let userMarker = null;
let userlat = null;
let userlng = null;

const map = L.map('mapid').setView([39.1312, -84.5167], 15);

// Function to get user location
function getUserLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(
			(position) => {
				userlat = position.coords.latitude;
				userlng = position.coords.longitude;
	
				// Add marker for user location
				userMarker = L.marker([userlat, userlng]).addTo(map);
			},
			(error) => {
				alert("Error getting user location: " + error);
			}
		);
	} else {
		alert("Geolocation is not supported by this browser.");
	}
}

// Call function to get user location
getUserLocation();

// Add OpenStreetMap tile layer to map
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors',
	maxZoom: 18
}).addTo(map);

// Define icons
const greenIcon = L.icon({
	iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
	shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-shadow.png',
	iconSize: [25, 41],
	iconAnchor: [12, 41],
	popupAnchor: [1, -34],
	shadowSize: [41, 41]
});

const redIcon = L.icon({
	iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
	shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-shadow.png',
	iconSize: [25, 41],
	iconAnchor: [12, 41],
	popupAnchor: [1, -34],
	shadowSize: [41, 41]
});

// Define variables for UI elements
const nearbyDiv = document.getElementById('nearby');
const directionsBtn = document.getElementById('directions-btn');
const garageDetailsDiv = document.getElementById('garage-details');

let selectedMarker = null;

// Function to display garage details
function displayGarageDetails(facility) {
	const content = `
		<h3 class="mb-4">${facility.name}</h3>
		<p>${facility.available} Available Parking Spots<br />
		${facility.capacity} Total Parking Spots</p>
	`;
	garageDetailsDiv.innerHTML = content;
	nearbyDiv.style.display = 'flex';
	selectedMarker = facility.marker;
}

// Loop through facilities and add markers to the map
// Bind click event to each marker
facilities.forEach((facility) => {
	facility.marker = L.marker([facility.lat, facility.lng], { icon: facility.available > 0 ? greenIcon : redIcon }).addTo(map);

	facility.marker.on('click', () => {
		displayGarageDetails(facility);
	});
});

// Open directions in Google Maps
function openDirectionsLink(lat, lng) {
  if (selectedMarker) {
    const directionsLink = `https://www.google.com/maps/dir/?api=1&origin=${userlat},${userlng}&destination=${lat},${lng}`;
    window.open(directionsLink, '_blank');
  }
}

// Bind click event to open directions link
directionsBtn.addEventListener('click', function() {
	if (selectedMarker) {
		openDirectionsLink(selectedMarker.getLatLng().lat, selectedMarker.getLatLng().lng);
	}
});
