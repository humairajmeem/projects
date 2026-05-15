// Tailwind CSS CDN auto add
const tailwindScript = document.createElement("script");
tailwindScript.src = "https://cdn.tailwindcss.com";
document.head.appendChild(tailwindScript);

// Navbar HTML
const navbar = document.createElement("nav");

navbar.innerHTML = `
  <div class="fixed top-0 left-0 w-full bg-cyan-950/90 backdrop-blur-md z-50 border-b border-gray-800">
    <div class="max-w-6xl mx-auto px-6 py-4 flex items-center justify-between">

      <!-- Logo -->
      <h2 class="text-2xl font-bold text-cyan-400">
        My Portfolio
      </h2>

      <!-- Desktop Menu -->
      <ul class="hidden md:flex items-center gap-8 text-gray-300 font-medium">
        <li><a href="hero_section.html" class="hover:text-cyan-400 transition">Home</a></li>
        <li><a href="about.html" class="hover:text-cyan-400 transition">About</a></li>
        <li><a href="projects.html" class="hover:text-cyan-400 transition">Projects</a></li>
        <li><a href="contact.html" class="hover:text-cyan-400 transition">Contact</a></li>
      </ul>

      <!-- Mobile Button -->
      <button id="menuBtn" class="md:hidden text-white text-3xl">
        ☰
      </button>
    </div>

    <!-- Mobile Menu -->
    <div id="mobileMenu" class="hidden md:hidden bg-gray-900 px-6 pb-4">
      <a href="hero_section.html" class="block py-2 text-gray-300 hover:text-cyan-400">Home</a>
      <a href="about.html" class="block py-2 text-gray-300 hover:text-cyan-400">About</a>
      <a href="projects.html" class="block py-2 text-gray-300 hover:text-cyan-400">Projects</a>
      <a href="contact.html" class="block py-2 text-gray-300 hover:text-cyan-400">Contact</a>
    </div>
  </div>
`;


document.body.prepend(navbar);

// Mobile menu toggle
setTimeout(() => {
  const menuBtn = document.getElementById("menuBtn");
  const mobileMenu = document.getElementById("mobileMenu");

  menuBtn.addEventListener("click", () => {
    mobileMenu.classList.toggle("hidden");
  });
}, 100);