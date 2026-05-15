function differentiate() {
  const xs = document.getElementById("x-values").value.split(",").map(Number);
  const ys = document.getElementById("y-values").value.split(",").map(Number);
  const x = parseFloat(document.getElementById("x-point").value);
  const method = document.getElementById("method").value;
  const order = parseInt(document.getElementById("order").value);

  if (xs.length !== ys.length || xs.length < 3) {
    alert("Enter at least 3 equal-length x and f(x) values.");
    return;
  }

  const h = xs[1] - xs[0];
  const u = (x - xs[0]) / h;
  const n = xs.length;
  const diffs = [ys.slice()];
  for (let i = 1; i < n; i++) {
    const prev = diffs[i - 1];
    const curr = [];
    for (let j = 0; j < prev.length - 1; j++) {
      curr.push(prev[j + 1] - prev[j]);
    }
    diffs.push(curr);
  }

  let result = 0, formula = "", sub = "";

  if (method === "forward") {
    if (order === 1) {
      result = (diffs[1][0] - diffs[2][0] / 2 + diffs[3]?.[0] / 3 || 0) / h;
      formula = "f'(x₀) ≈ (Δy₀ - ½Δ²y₀ + ⅓Δ³y₀)/h";
      sub = `(${diffs[1][0]} - ${diffs[2][0]}/2 + ${diffs[3]?.[0] || 0}/3)/${h}`;
    } else {
      result = (diffs[2][0] - diffs[3]?.[0] + diffs[4]?.[0] / 2 || 0) / (h * h);
      formula = "f''(x₀) ≈ (Δ²y₀ - Δ³y₀ + ½Δ⁴y₀)/h²";
      sub = `(${diffs[2][0]} - ${diffs[3]?.[0] || 0} + ${diffs[4]?.[0] || 0}/2)/(${h}²)`;
    }
  } else if (method === "backward") {
    const last = n - 1;
    if (order === 1) {
      result = (diffs[1][last - 1] + diffs[2][last - 2] / 2 + diffs[3]?.[last - 3] / 3 || 0) / h;
      formula = "f'(xₙ) ≈ (∇yₙ + ½∇²yₙ + ⅓∇³yₙ)/h";
      sub = `(${diffs[1][last - 1]} + ${diffs[2][last - 2]}/2 + ${diffs[3]?.[last - 3] || 0}/3)/${h}`;
    } else {
      result = (diffs[2][last - 2] + diffs[3]?.[last - 3] + diffs[4]?.[last - 4] / 2 || 0) / (h * h);
      formula = "f''(xₙ) ≈ (∇²yₙ + ∇³yₙ + ½∇⁴yₙ)/h²";
      sub = `(${diffs[2][last - 2]} + ${diffs[3]?.[last - 3] || 0} + ${diffs[4]?.[last - 4] || 0}/2)/(${h}²)`;
    }
  } else if (method === "stirling") {
    const mid = Math.floor(n / 2);
    if (order === 1) {
      const d1 = (diffs[1][mid] + diffs[1][mid - 1]) / 2;
      const d3 = diffs[3] ? (diffs[3][mid] + diffs[3][mid - 1]) / 2 : 0;
      result = (d1 - d3 / 12) / h;
      formula = "f'(x₀) ≈ (Δy₀ + ∇y₀)/2 - (Δ³y₀ + ∇³y₀)/12";
      sub = `(${diffs[1][mid]} + ${diffs[1][mid - 1]})/2 - (${diffs[3]?.[mid] || 0} + ${diffs[3]?.[mid - 1] || 0})/12)/${h}`;
    } else {
      const d2 = diffs[2][mid - 1];
      const d4 = diffs[4]?.[mid - 2] || 0;
      result = (d2 - d4 / 12) / (h * h);
      formula = "f''(x₀) ≈ Δ²y₀ - (Δ⁴y₀)/12";
      sub = `(${d2} - ${d4}/12)/(${h}²)`;
    }
  } else if (method === "divided") {
    result = dividedDifference(xs, ys, x);
    formula = "Using Newton’s Divided Difference";
    sub = "Auto-computed for arbitrary x";
  }

  document.getElementById("result-formula").innerHTML = `\\[${formula}\\]`;
  document.getElementById("result-substitution").innerHTML = `\\[${sub}\\]`;
  document.getElementById("result-derivative").innerHTML = `\\[f^{(${order})}(${x}) ≈ ${result.toFixed(6)}\\]`;
  document.getElementById("result-u-h").innerHTML = `\\[h = ${h}, \\quad u = \\frac{${x} - ${xs[0]}}{${h}} = ${u.toFixed(3)}\\]`;

  MathJax.typeset();
  plotGraph(xs, ys, x);
}

function compareMethods() {
  const methods = ["forward", "backward", "stirling", "divided"];
  const xs = document.getElementById("x-values").value.split(",").map(Number);
  const ys = document.getElementById("y-values").value.split(",").map(Number);
  const x = parseFloat(document.getElementById("x-point").value);
  const order = parseInt(document.getElementById("order").value);

  let output = `<h3>Comparison of Methods for f<sup>(${order})</sup>(${x}):</h3><table border="1" cellpadding="10"><tr><th>Method</th><th>Result</th></tr>`;

  methods.forEach(method => {
    document.getElementById("method").value = method;
    differentiate();  // This updates result in the UI
    const result = document.getElementById("result-derivative").innerText;
    output += `<tr><td>${method.charAt(0).toUpperCase() + method.slice(1)}</td><td>${result}</td></tr>`;
  });

  output += "</table>";

  const section = document.querySelector(".container");
  let compareCard = document.getElementById("compare-card");
  if (!compareCard) {
    compareCard = document.createElement("section");
    compareCard.className = "card";
    compareCard.id = "compare-card";
    section.appendChild(compareCard);
  }
  compareCard.innerHTML = output;
}

function dividedDifference(xs, ys, x) {
  const n = xs.length;
  const dd = Array.from({ length: n }, () => Array(n).fill(0));
  for (let i = 0; i < n; i++) dd[i][0] = ys[i];
  for (let j = 1; j < n; j++) {
    for (let i = 0; i < n - j; i++) {
      dd[i][j] = (dd[i + 1][j - 1] - dd[i][j - 1]) / (xs[i + j] - xs[i]);
    }
  }
  let val = dd[0][1]; // First derivative
  if (isNaN(val)) val = 0;
  return val;
}

function plotGraph(xs, ys, x) {
  const ctx = document.getElementById("graph").getContext("2d");
  const data = {
    labels: xs,
    datasets: [{
      label: 'f(x)',
      data: ys,
      borderColor: 'blue',
      fill: false,
      tension: 0.1
    }]
  };
  if (window.myChart) window.myChart.destroy();
  window.myChart = new Chart(ctx, {
    type: 'line',
    data,
    options: {
      responsive: false,
      plugins: {
        legend: { display: true }
      },
      scales: {
        x: { title: { display: true, text: 'x' } },
        y: { title: { display: true, text: 'f(x)' } }
      }
    }
  });
}

function exportGraph() {
  const canvas = document.getElementById("graph");
  const a = document.createElement("a");
  a.href = canvas.toDataURL("image/png");
  a.download = "graph.png";
  a.click();
}
