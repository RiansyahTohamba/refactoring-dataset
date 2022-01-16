DROP VIEW MethodsAndTypes;
CREATE VIEW MethodsAndTypes AS 
SELECT 
Parameters.type as type_id, 
Parameters.method as method_id, Parameters.externalType, Types.parent AS package, Types.name AS type, Methods.name AS method, position
FROM Parameters 
LEFT JOIN Measurables AS Types ON Types.id = Parameters.type 
LEFT JOIN Measurables AS Methods ON Methods.id = method

