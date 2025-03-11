
from fastapi import FastAPI, File, UploadFile
import whisper
import uvicorn
import os

app = FastAPI()

# Cargar el modelo Whisper Tiny
model = whisper.load_model("tiny")

@app.post("/transcribe/")
async def transcribe_audio(file: UploadFile = File(...)):
    try:
        # Guardar temporalmente el archivo
        file_location = f"temp_{file.filename}"
        with open(file_location, "wb") as buffer:
            buffer.write(await file.read())
        
        # Transcribir el audio
        result = model.transcribe(file_location)
        
        # Eliminar el archivo temporal
        os.remove(file_location)
        
        return {"transcription": result["text"]}
    except Exception as e:
        return {"error": str(e)}

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
