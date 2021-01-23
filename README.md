# product-catalog-service

# This is simple Product Catalog Service with options to filter, sort and paginate products.

# Sellers Api
1. Add Seller POST: {endpoint}/sellers 
2. get Sellers GET: {endpoint}/sellers
3. Update existing Seller PUT: {endpoint}/sellers
4. Delete existing Seller DELETE: {endpoint}/sellers
5. Add Product for Seller POST: {endpoint}/sellers/{sellerId}/products
6. Delete Product for Seller DELETE: {endpoint}/sellers/{sellerId}/products/{productId}

# Product API (Fetch Api's to be Consume by clients)

1. Fetch All Products GET {endpoint}/products

# filter Options:
	1. filter By Brand: /products?brand={brand}
	2. filter By size: /products?size={size}
	3. filter By Color: /products?color={color}
	4. filter By Material: /products?material={material}
	all above query parameters are Optional and we can used them in combination too.
	eg. /products?brand={brand}&size={size}&color={color}&material={material}
	
# Pagination:	
	1. Offset and sizeOfPage query params are used for pagination. both are optional we can use any one or both of them with above filters.
	eg /products?offset={offset}&sizeOfPage={sizeOfPage}
	
# using Filters and Pagination togather
eg.  GET: /products?brand={brand}&size={size}&color={color}&material={material}&offset={offset}&sizeOfPage={sizeOfPage}