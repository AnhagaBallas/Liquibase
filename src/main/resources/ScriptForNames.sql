SELECT product_name
FROM netology.ORDERS A
         JOIN netology.CUSTOMERS B
              ON A.id = B.id
where B.name = :name;
