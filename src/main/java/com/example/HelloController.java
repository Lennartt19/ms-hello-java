package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.http.MediaType;

@RestController
public class HelloController {

    @Autowired
    private SecretService secretService;

    @Value("${secreto.vars:SECRET_VALUE,APP_NAME,APP_VERSION,JAVA_VERSION,SPRING_PROFILES_ACTIVE,HOSTNAME,USER,HOME,PWD,PATH}")
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
                                                    <title>Saludo DevOps — Hola Leonardo</title>
                                                    <style>
                                                        :root{--bg:#02050b;--panel:#081426;--panel-2:#0b1b33;--neon:#32f5e8;--cyan:#60a5fa;--violet:#8b5cf6;--muted:#9fb0c4;--line:rgba(255,255,255,0.08)}
                                                        *{box-sizing:border-box}
                                                        body{margin:0;min-height:100vh;display:grid;place-items:center;padding:24px;background:radial-gradient(1000px 600px at 10% 10%, rgba(139,92,246,0.08), transparent),radial-gradient(900px 500px at 85% 15%, rgba(50,245,232,0.08), transparent),linear-gradient(180deg,var(--bg),#031026);font-family:Inter,ui-sans-serif,system-ui,Segoe UI,Roboto,Arial;color:#e9fbf8}
                                                        body::before{content:"";position:fixed;inset:0;background-image:linear-gradient(rgba(255,255,255,0.03) 1px, transparent 1px),linear-gradient(90deg,rgba(255,255,255,0.03) 1px, transparent 1px);background-size:44px 44px;opacity:0.08;pointer-events:none}
                                                        .shell{width:min(1040px,94%);padding:30px;border-radius:24px;background:linear-gradient(180deg,rgba(255,255,255,0.05),rgba(0,0,0,0.18));border:1px solid var(--line);box-shadow:0 30px 80px rgba(2,6,23,0.7);position:relative;overflow:hidden;backdrop-filter:blur(16px)}
                                                        .shell::before{content:"";position:absolute;inset:0;background:linear-gradient(120deg, rgba(50,245,232,0.08), transparent 35%, rgba(139,92,246,0.08));pointer-events:none}
                                                        .glow{position:absolute;right:-120px;top:-80px;width:360px;height:360px;border-radius:50%;background:radial-gradient(circle at 30% 30%,var(--neon),transparent 42%);opacity:0.12;filter:blur(36px)}
                                                        .head{display:grid;grid-template-columns:auto 1fr;gap:18px;align-items:center;position:relative;z-index:1}
                                                        .mark{width:84px;height:84px;border-radius:20px;background:conic-gradient(from 200deg,var(--neon),var(--cyan),var(--violet));display:grid;place-items:center;font-weight:900;color:#021226;box-shadow:0 0 0 1px rgba(255,255,255,0.14),0 18px 42px rgba(50,245,232,0.16)}
                                                        .eyebrow{display:inline-flex;gap:8px;align-items:center;padding:6px 10px;border-radius:999px;background:rgba(255,255,255,0.04);border:1px solid rgba(255,255,255,0.08);color:var(--muted);font-size:12px;letter-spacing:0.12em;text-transform:uppercase}
                                                        h1{margin:10px 0 10px;font-size:clamp(30px,4vw,48px);line-height:1.02;letter-spacing:-0.04em}
                                                        p{margin:0;color:var(--muted);line-height:1.7;max-width:60ch}
                                                        .buttons{display:flex;flex-wrap:wrap;gap:12px;margin-top:18px}
                                                        .btn{padding:12px 16px;border-radius:12px;color:#021226;font-weight:800;text-decoration:none;background:linear-gradient(90deg,var(--neon),var(--cyan));box-shadow:0 12px 28px rgba(50,245,232,0.12);transition:transform 160ms ease, box-shadow 160ms ease}
                                                        .btn:hover{transform:translateY(-1px);box-shadow:0 16px 32px rgba(50,245,232,0.18)}
                                                        .btn.secondary{background:transparent;color:var(--neon);border:1px solid rgba(255,255,255,0.12);box-shadow:none}
                                                        .panels{display:grid;grid-template-columns:repeat(3,minmax(0,1fr));gap:12px;margin-top:22px;position:relative;z-index:1}
                                                        .card{padding:16px;border-radius:16px;background:linear-gradient(180deg,rgba(255,255,255,0.03),rgba(0,0,0,0.08));border:1px solid rgba(255,255,255,0.05)}
                                                        .card h3{margin:0 0 8px;font-size:14px;text-transform:uppercase;letter-spacing:0.14em;color:#c8f8ff}
                                                        .card p{font-size:14px;line-height:1.65}
                                                        .bar{margin-top:18px;height:8px;border-radius:999px;background:rgba(255,255,255,0.05);overflow:hidden;border:1px solid rgba(255,255,255,0.08)}
                                                        .bar span{display:block;height:100%;width:72%;border-radius:inherit;background:linear-gradient(90deg,var(--neon),var(--cyan),var(--violet));box-shadow:0 0 18px rgba(50,245,232,0.18)}
                                                        footer{margin-top:18px;color:var(--muted);font-size:13px;text-align:right;position:relative;z-index:1}
                                                        @media (max-width: 820px){.head,.panels{grid-template-columns:1fr}.mark{width:72px;height:72px}.shell{padding:22px}}
                                                    </style>
                                                </head>
                                                <body>
                                                    <div class=\"shell\">
                                                        <div class=\"glow\"></div>
                                                        <div class=\"head\">
                                                            <div class=\"mark\">DEV</div>
                                                            <div>
                                                                <span class=\"eyebrow\">Welcome / DevOps Dashboard</span>
                                                                <h1>Hola Leonardo, tu entorno está listo para desplegar</h1>
                                                                <p>Una página de saludo más limpia, moderna y técnica. Pensada para mostrar el acceso al servicio, resaltar el stack y acompañar demos de infraestructura como código.</p>
                                                                <div class=\"buttons\">
                                                                    <a class=\"btn\" href=\"/secreto\">Abrir Secretos</a>
                                                                    <a class=\"btn secondary\" href=\"/\">Volver al Portal</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class=\"panels\">
                                                            <div class=\"card\">
                                                                <h3>Pipeline</h3>
                                                                <p>Build → Test → Package → Image → Deploy. Flujo visual orientado a automatización continua.</p>
                                                            </div>
                                                            <div class=\"card\">
                                                                <h3>Observability</h3>
                                                                <p>Métricas, logs y trazas con una capa visual que transmite operación y control.</p>
                                                            </div>
                                                            <div class=\"card\">
                                                                <h3>Runtime</h3>
                                                                <p>Spring Boot, Docker y variables de entorno para demos de plataforma y secretos.</p>
                                                            </div>
                                                        </div>

                                                        <div class=\"bar\"><span></span></div>

                                                        <footer>© Proyecto IaC — Saludo DevOps para demos y control de entorno</footer>
                                                    </div>
                                                </body>
                                                </html>
                                                """;
        }

        @GetMapping("/secreto")
        public ResponseEntity<Map<String, Object>> secreto() {
        String value = secretService.getSecret();
        Map<String, Object> resp = new HashMap<>();

        resp.put("secret", value != null ? value : "<no-set>");

        // determine source
        String source = (System.getenv("SECRET_VALUE") != null && !System.getenv("SECRET_VALUE").isBlank())
                ? "env:SECRET_VALUE" : (value != null ? "fallback" : "none");
        resp.put("source", source);

        // decide which vars to show: environment variable SECRETO_VARS takes precedence
        String vars = System.getenv().getOrDefault("SECRETO_VARS", secretoVars);
        Map<String, String> visibleVars = new LinkedHashMap<>();
        Stream.of(vars.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
            .forEach(name -> visibleVars.put(name, System.getenv().getOrDefault(name, "<no-set>")));

        resp.put("visible_variables", visibleVars);
        resp.put("visible_variables_count", visibleVars.size());

        // metadata
        resp.put("timestamp", Instant.now().toString());

        return ResponseEntity.ok(resp);
    }
}
