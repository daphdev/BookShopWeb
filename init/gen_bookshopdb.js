// user
[
    '{{repeat(5)}}',
    {
        id: 'default',
        username: 'user{{index() + 1}}',
        password: '202CB962AC59075B964B07152D234B70',
        fullname: '{{firstName()}}' + ' ' + '{{surname()}}',
        email: '{{email()}}',
        phoneNumber: 'x09{{integer(10000000, 99999999)}}',
        gender: '{{gender() === "male" ? 0 : 1}}',
        address: '{{integer(1, 50)}}' + ' ' + '{{street()}}' + ', ' + '{{city()}}' + ', ' + '{{country()}}'
    }
]

// user_roles
[
    '{{repeat(5)}}',
    {
        username: 'user{{index() + 1}}',
        role: ''
    }
]

// product
[
    '{{repeat(100)}}',
    {
        name: function (tags) {
            return 'Sách ' + tags.company();
        },
        price: '{{integer(5000, 500000)}}',
        discount: '{{random(0, 20)}}',
        quantity: '{{integer(10, 100)}}',
        totalBuy: '{{integer(10, 500)}}',
        author: '{{firstName()}}' + ' ' + '{{surname()}}',
        pages: '{{integer(10, 500)}}',
        publisher: 'NXB {{random("Giáo dục", "Đại học Sư phạm Hà Nội", "Đại học Sư phạm TP.HCM", "Đại học Quốc gia Hà Nội", "Đại học Huế")}}',
        yearPublishing: '{{integer(1995, 2021)}}',
        description: '{{lorem(2, "paragraphs")}}',
        imageName: '{{random("temp-12235989262213754276.jpg", "temp-14438611480196141526.jpg", "temp-7329036107498680084.jpg", "temp-17624438115898823949.jpg", "temp-6243426685116508297.jpg", "temp-8476700387786158058.jpg", "temp-13862094760385571107.jpg", "temp-13064240004351430671.jpg", "temp-3984373128647845854.jpg", "temp-10075522682831764585.jpg", "temp-3015888053636485125.jpg", "temp-8262627340495498759.jpg", "temp-18128511448457962576.jpg", "temp-6352099207348952932.jpg", "temp-16741118072528735594.jpg")}}',
        shop: '{{integer(0, 1)}}',
        createdAt: '{{date(new Date(2021, 1, 1), new Date(2021, 12, 31), "YYYY-MM-dd HH:mm:ss")}}',
        updatedAt: null,
        startsAt: null,
        endsAt: null
    }
]

// product_review
[
    '{{repeat(150)}}',
    {
        userId: '{{integer(1, 5)}}',
        productId: '{{integer(1, 100)}}',
        ratingScore: '{{integer(1, 5)}}',
        content: '{{lorem(3, "sentences")}}',
        createdAt: '{{date(new Date(2021, 1, 1), new Date(2021, 12, 31), "YYYY-MM-dd HH:mm:ss")}}',
        updatedAt: null
    }
]

// category
[
    '{{repeat(15)}}',
    {
        name: function (tags, index) {
            var names = [
                'Sách giáo khoa',
                'Sách khoa học',
                'Truyện tranh',
                'Tiểu thuyết',
                'Truyện ngắn',
                'Truyện dài',
                'Sách giáo trình',
                'Báo in',
                'Tạp chí',
                'Tập san',
                'Sách nấu ăn',
                'Sách kỹ thuật',
                'Sách nông nghiệp',
                'Sách thiếu nhi',
                'Sách kỹ năng sống'
            ];
            return names[index];
        },
        description: '{{lorem(1, "sentences")}}',
        imageName: '50px.png'
    }
]

// product_category
[
    '{{repeat(100)}}',
    {
        productId: '{{index(1)}}',
        categoryId: '{{integer(1, 15)}}'
    }
]

// cart
[
    '{{repeat(2)}}',
    {
        userId: '{{index(4)}}',
        createdAt: '{{date(new Date(2021, 1, 1), new Date(2021, 12, 31), "YYYY-MM-dd HH:mm:ss")}}',
        updatedAt: null
    }
]

// cart_item
[
    '{{repeat(5)}}',
    {
        cartId: '{{integer(1, 2)}}',
        productId: '{{integer(1, 100)}}',
        quantity: '{{integer(1, 5)}}',
        createdAt: '{{date(new Date(2021, 1, 1), new Date(2021, 12, 31), "YYYY-MM-dd HH:mm:ss")}}',
        updatedAt: null
    }
]

// orders
[
    '{{repeat(25)}}',
    {
        userId: '{{integer(4, 5)}}',
        status: '{{integer(1, 3)}}',
        deliveryMethod: '{{index() % 2 + 1}}',
        deliveryPrice: '{{index() % 2 + 1 === 1 ? 10000 : 50000}}',
        createdAt: '{{date(new Date(2021, 1, 1), new Date(2021, 12, 31), "YYYY-MM-dd HH:mm:ss")}}',
        updatedAt: null
    }
]

// order_item
[
    '{{repeat(60)}}',
    {
        orderId: '{{index() % 25 + 1}}',
        productId: '{{integer(1, 100)}}',
        price: '{{integer(5000, 500000)}}',
        discount: '{{random(0, 20)}}',
        quantity: '{{integer(1, 5)}}',
        createdAt: '{{date(new Date(2021, 1, 1), new Date(2021, 12, 31), "YYYY-MM-dd HH:mm:ss")}}',
        updatedAt: null
    }
]


// wishlist_item
[
    '{{repeat(30)}}',
    {
        userId: '{{integer(4, 5)}}',
        productId: '{{index(1)}}',
        createdAt: '{{date(new Date(2021, 1, 1), new Date(2021, 12, 31), "YYYY-MM-dd HH:mm:ss")}}',
        updatedAt: null
    }
]
