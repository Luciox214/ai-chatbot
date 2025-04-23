package ai.chatbot.controllers;
import ai.chatbot.services.LlamaAIService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class OllamaController {

    @Autowired
    private LlamaAIService llamaAIService;

    @GetMapping("/generate")
    public String generate(@RequestParam("prompt") String prompt) {
        return llamaAIService.generateResult(prompt);
    }

    @PostMapping(value = "/revisar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String revisarTextoLibre(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "comentario", required = false) String comentario
    ) throws IOException {

        StringBuilder textoFinal = new StringBuilder();

        // Extraer texto del PDF si se cargó
        if (file != null && !file.isEmpty()) {
            try (PDDocument document = PDDocument.load(file.getInputStream())) {
                PDFTextStripper stripper = new PDFTextStripper();
                textoFinal.append(stripper.getText(document)).append("\n\n");
            }
        }

        // Agregar comentario si existe
        if (comentario != null && !comentario.isBlank()) {
            textoFinal.append("Comentario del usuario: ").append(comentario).append("\n");
        }

        // Validación: si no hay nada, devolver error
        if (textoFinal.isEmpty()) {
            return "Debe cargar un PDF, escribir un comentario, o ambos.";
        }

        String prompt = "Quiero que analices el siguiente texto y revises su ortografía y gramática, simplemente respondeme el análisis de una manera estructurada directa al grano y sin mensajes previos, en formato de texto narrativo. :\n\n" + textoFinal;
        return llamaAIService.generateResult(prompt);
    }
}
