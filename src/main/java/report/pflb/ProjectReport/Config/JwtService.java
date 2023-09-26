package report.pflb.ProjectReport.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@NoArgsConstructor
public class JwtService {

    private final String KEY = "atLtUtFfrc4AeFBVxJkKIohyXfwbVR4qCa3RrLEc8qnpJG17RggFhlzvRtYcwQK34ecfx/RfatHvPaHzdu8rMKfwhl+X+boC5GmOnCLfX+SzfGTCt3pm/UOzg9XMW67HE/Xz+3O77Mtm6yYvLBee+BnH6qZwfz5nuoBB6xhZSnouOiPQZvmZHjo5VrZD9BZlehar/hwHqa+CvDcFV33hhk++qcVudA3uAjKBCEtBSZS/SzxxMG8nDFBBiubaeZOqXpTcE8klaYwBrRYtGfSQk+fFgn3SwJdnSHZJoKK2xS8q6CcEosvStU/0u+/OtEDSdET5w1/x/HS7/ZYn6sNcn8Wru4haEz3DssxkNY8cwbEls8zcM+2/EODeunp+sExFsa5dAj76mgnkFWhMl54vxJ1ashdZpxG3TM9Prq3+nFoU+3RH+qFh2nslzQZtP84TdV01fadfZc7EpeUhbRPMtvRtEFQUnHVEPoiuy2MC8qkFiuEC4ZphDkzQdDlhL14xj7vJv6aexqtd+D5z+FEjmz1YtGw5WJGlj0eswknR5zCSdv0FizV8EG42O4lSbADiD9P12P9Xq72FSZTIyhiYZIFUOfyvg0j7MCOyateksqlNoh3tpKooMTmfJ8HM/0lG1ovunZwqQU+KZozWvYdLlG5uKmv2jh6thD0uKq9rG4Sh4uSdyMDSGU4bxHeELYag";

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractLogin(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final  Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, 86400000);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean tokenValid(String token, UserDetails userDetails){
        final String login = extractLogin(token);
        return (login.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
