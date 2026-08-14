package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.http.MediaType;

@RestController
public class HelloController {

    @Autowired
    private SecretService secretService;

    @Value("${secreto.vars:SECRET_VALUE,USER,HOSTNAME}")
    private String secretoVars;

    @GetMapping("/hello")
    public String hello() {
        return "Hola Leonardo";
    }

        @GetMapping(value = "/saludo", produces = MediaType.TEXT_HTML_VALUE)
        public String saludo() {
                                return """
                                                <!doctype html>
                                                <html lang=\"es\">
                                                <head>
                                                    <meta charset=\"utf-8\">
                                                    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">
                                                    <title>Hola Leonardo — Nexus DevOps</title>
                                                    <style>
                                                        :root{--bg:#01040a;--panel:#041126;--neon:#00fff6;--violet:#8b5cf6;--muted:#9fb0c4}
                                                        *{box-sizing:border-box}
                                                        body{margin:0;height:100vh;display:grid;place-items:center;background:radial-gradient(1000px 600px at 10% 10%, rgba(139,92,246,0.06), transparent),linear-gradient(180deg,var(--bg),#031026);font-family:Inter,ui-sans-serif,system-ui,Segoe UI,Roboto,Arial;color:#e9fbf8}
                                                        .shell{width:840px;max-width:94%;padding:28px;border-radius:14px;background:linear-gradient(180deg,rgba(255,255,255,0.02),rgba(0,0,0,0.12));border:1px solid rgba(255,255,255,0.03);box-shadow:0 30px 80px rgba(2,6,23,0.7);position:relative;overflow:hidden}
                                                        .glow{position:absolute;right:-120px;top:-80px;width:360px;height:360px;border-radius:50%;background:radial-gradient(circle at 30% 30%,var(--neon),transparent 40%);opacity:0.08;filter:blur(40px)}
                                                        .head{display:flex;gap:14px;align-items:center}
                                                        .mark{width:72px;height:72px;border-radius:14px;background:conic-gradient(from 200deg,var(--neon),var(--violet));display:grid;place-items:center;font-weight:900;color:#021226}
                                                        h1{margin:0;font-size:30px}
                                                        p{margin:6px 0;color:var(--muted)}
                                                        .buttons{display:flex;gap:10px;margin-top:12px}
                                                        .btn{padding:10px 14px;border-radius:10px;color:#021226;font-weight:800;text-decoration:none;background:linear-gradient(90deg,var(--neon),var(--violet));box-shadow:0 8px 30px rgba(139,92,246,0.12)}
                                                        .btn.secondary{background:transparent;color:var(--neon);border:1px solid rgba(255,255,255,0.04)}
                                                        .panels{display:flex;gap:12px;margin-top:18px}
                                                        .card{flex:1;padding:12px;border-radius:10px;background:linear-gradient(180deg,rgba(255,255,255,0.01),rgba(0,0,0,0.06));border:1px solid rgba(255,255,255,0.02)}
                                                        .card h3{margin:0 0 8px}
                                                        footer{margin-top:14px;color:var(--muted);font-size:13px;text-align:right}
                                                    </style>
                                                </head>
                                                <body>
                                                    <div class=\"shell\">
                                                        <div class=\"glow\"></div>
                                                        <div class=\"head\">
                                                            <div class=\"mark\">DEV</div>
                                                            <div>
                                                                <h1>Hola Leonardo — Nexus DevOps</h1>
                                                                <p>Panel interactivo con enlaces y flujo visual inspirado en pipelines y observabilidad.</p>
                                                                <div class=\"buttons\">
                                                                    <a class=\"btn\" href=\"/saludo\">Visitar Saludo</a>
                                                                    <a class=\"btn secondary\" href=\"/secreto\">Ver Secretos (JSON)</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class=\"panels\">
                                                            <div class=\"card\">
                                                                <h3>Pipeline</h3>
                                                                <p>Build → Test → Image → Deploy. Automatizaciones y pipelines declarativos.</p>
                                                            </div>
                                                            <div class=\"card\">
                                                                <h3>Telemetry</h3>
                                                                <p>Métricas tiempo real, logs estructurados y alertas basadas en SLIs/SLOs.</p>
                                                            </div>
                                                        </div>

                                                        <footer>© Proyecto IaC — Estética futurista DevOps</footer>
                                                    </div>
                                                </body>
                                                </html>
                                                """;
        }

    @GetMapping("/secreto")
    public ResponseEntity<Map<String, String>> secreto() {
        String value = secretService.getSecret();
        Map<String, String> resp = new HashMap<>();

        resp.put("secret", value != null ? value : "<no-set>");

        // determine source
        String source = (System.getenv("SECRET_VALUE") != null && !System.getenv("SECRET_VALUE").isBlank())
                ? "env:SECRET_VALUE" : (value != null ? "fallback" : "none");
        resp.put("source", source);

        // decide which vars to show: environment variable SECRETO_VARS takes precedence
        String vars = System.getenv().getOrDefault("SECRETO_VARS", secretoVars);
        Stream.of(vars.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .forEach(name -> resp.put(name, System.getenv().getOrDefault(name, "<no-set>")));

        // metadata
        resp.put("timestamp", Instant.now().toString());

        return ResponseEntity.ok(resp);
    }
}
