<div>
    <label for="${name}">${label}:</label>
    <input type="button" value=" up " onclick="increase('${name}')" id="${name}Up">
    <input type="number" name="${name}" id="${name}" value="${value}">
    <input type="button" value="down" onclick="decrease('${name}')" id="${name}Down">
</div>