document.getElementById("convertButton").addEventListener("click", function () {
    // Create SVG element
    const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svg.setAttribute("width", "400");
    svg.setAttribute("height", "300");

    // Convert HTML to SVG
    const sourceDiv = document.getElementById("sourceDiv");
    const clonedDiv = sourceDiv.cloneNode(true);
    svg.appendChild(clonedDiv);

    // Convert SVG to Blob and display as image
    const svgData = new XMLSerializer().serializeToString(svg);
    const blob = new Blob([svgData], {type: "image/svg+xml;charset=utf-8"});
    const svgURL = URL.createObjectURL(blob);

    const img = new Image();
    img.src = svgURL;
    document.getElementById("outputContainer").appendChild(img);
});