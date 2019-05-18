<div>
    <label for="${name}">${label}:</label>
    <input type="button" value=" + " onclick="increase('${name}')" id="${name}Up">
    <input type="number" name="${name}" id="${name}" value="${value}" min="0" max="10" limitReachedMsg="This score is not possible">
    <input type="button" value=" - " onclick="decrease('${name}')" id="${name}Down">
</div>