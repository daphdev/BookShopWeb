package com.bookshopweb;

import com.bookshopweb.service.CategoryService;
import com.bookshopweb.service.ProductReviewService;
import com.bookshopweb.service.ProductService;
import com.bookshopweb.service.UserService;
import com.bookshopweb.utils.Validator;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        UserService userService = new UserService();

//        List<User> users = userService.getAll();
//        System.out.println(users);
//
//        Optional<User> opt1 = userService.getById(1);
//        System.out.println(opt1.orElseGet(User::new));
//
//        Optional<User> opt2 = userService.getByUsername("admin02");
//        System.out.println(opt2.orElseGet(User::new));

//        try {
//            Long id = userService.insert(new User(0, "admin08", "www"));
//            System.out.println(id);
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }

//        try {
//            userService.update(new User(20, "admin08", "def"));
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }

//        try {
//            userService.delete(15);
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }

//        System.out.println(test("hello", "hello2"));

        Validator validator = new Validator();
//        System.out.println(validator.isNulorderItemCustomsring(null));
//        System.out.println(validator.isNulorderItemCustomsring("abc"));
//        Optional<String> opt = Optional.ofNullable(null);
//        opt.ifPresent(System.out::println);

//        System.out.println(Validator.isAtMostOfLength2(null, 3));
//        System.out.println(Validator.isAtMostOfLength2("1", 3));
//        System.out.println(Validator.isAtMostOfLength2("123", 3));
//        System.out.println(Validator.isAtMostOfLength2("1234", 3));
//        System.out.println("===================");
//        System.out.println(validator.isAtLeast(null, 3));
//        System.out.println(validator.isAtLeast("1", 3));
//        System.out.println(validator.isAtLeast("123", 3));
//        System.out.println(validator.isAtLeast("1234", 3));
//
//        System.out.println("=========");
//        System.out.println(validator.isNotEmpty(null));
//        System.out.println(validator.isNotEmpty(""));
//        System.out.println(validator.isNotEmpty("        "));
//        System.out.println(validator.isNotEmpty(" eee "));
//
//        System.out.println("=======");
//        System.out.println(validator.isNotNull(null));
//        System.out.println(validator.isNotNull(""));
//        System.out.println(validator.isNotNull("aaa"));
//
//        System.out.println("=======");
//        System.out.println(validator.hasPattern(null, Arrays.asList("email", "\\d+")));
//        System.out.println(validator.hasPattern("null", Arrays.asList("email", "\\d+")));
//        System.out.println(validator.hasPattern("0912", Arrays.asList("email", "\\d+")));
//
//        System.out.println("=======");
//        System.out.println(validator.isNotNullAndEmpty(null));
//        System.out.println(validator.isNotNullAndEmpty(""));
//        System.out.println(validator.isNotNullAndEmpty("        "));
//        System.out.println(validator.isNotNullAndEmpty(" eee "));

        // Kiểm tra Validator
        String username = "fff3";
        List<String> violations1 = Validator.of(username)
//                .validate(Validator::isNotNull)
//                .validate(Validator::isNotNullAndEmpty)
//                .validate(Validator::isNotEmpty)
//                .validate(Validator::isAtMostOfLength, 3)
//                .validate(Validator::isAtLeastOfLength, 2)
//                .validate(Validator::hasPattern, "\\d+", "số điện thoại")
//                .validate(Validator::isNotBlankAtBothEnds)
                .toList();
        System.out.println("violations1: " + violations1);

        String password = "null";
        List<String> violations2 = Validator.of(password)
//                .validate(Validator::isNotNullAndEmpty)
//                .validate(Validator::isAtMostOfLength, 3)
//                .validate(Validator::isEqualTo, "ab", "Mật khẩu")
                .toList();
        System.out.println("violations2: " + violations2);

        String password2 = "null";
        List<String> violations3 = Validator.of(password2)
                .isNotNullAndEmpty()
                .isAtMostOfLength(3)
                .isEqualTo("ab", "Mật khẩu")
                .toList();
        System.out.println("violations3: " + violations3);

//        Optional<String> shouldNotBeEmpty = Optional.empty();
//        shouldNotBeEmpty.orElseThrow(() -> new IllegaorderItemCustomsateException("This should not happen!!!"));
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

//
//        System.out.println("===========================");
//
//        System.out.println("userService.getByUsername(null): " + userService.getByUsername(null).map(User::getPassword).orElseGet(String::new));
//        System.out.println("userService.getByUsername(aa): " + userService.getByUsername("aa").map(User::getPassword).orElseGet(String::new));
//        System.out.println("userService.getByUsername(w): " + userService.getByUsername("w").map(User::getPassword).orElseGet(String::new));
//
//        System.out.println(new String().equals(" "));

//        Map<String, List<String>> violations = new HashMap<>();
//        violations.put("usernameViolations", Arrays.asList("1", "2"));
//        violations.put("passwordViolations", Collections.emptyList());
//        violations.put("policyViolations", Arrays.asList("3", "4", "5"));
//
//        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
//        System.out.println(sumOfViolations);
//
//        String username = "acb";
//        String password = "assddd";
//        String policy = "";
//
//        Map<String, String> collectorMapOfLists = Stream.of(username, password, policy)
//                .collect(Collectors.toMap(String::valueOf, String::valueOf));
//
//        System.out.println(collectorMapOfLists);

//        String password = "ILoveJava";
//        System.out.println(HashingUtils.hash(password));
//        System.out.println(HashingUtils.verify(password, HashingUtils.hash(password)));

//        System.out.println("Kiểm tra 2");
//        String pass1 = "ILoveJava";
//        String passHash1 = "35454B055CC325EA1AF2126E27707052";
//        List<String> vio1 = Validator.of(pass1)
//                .validate(Validator::isNotNullAndEmpty)
//                .validate(Validator::isAtMostOfLength, 32)
//                .changeTo(HashingUtils.hash(pass1))
//                .validate(Validator::isEqualTo, passHash1, "Mật khẩu")
//                .toList();
//        System.out.println(vio1);
//        System.out.println(HashingUtils.hash(pass1));

        System.out.println("Kiểm tra Protector");

//        System.out.println("userService.getByUsername(null): " + userService.getByUsername(null));
//        System.out.println("userService.getByUsername(aa): " + userService.getByUsername("aa"));
//        System.out.println("userService.getByUsername(w): " + userService.getByUsername("w"));

//        System.out.println("Protector 1: " + Protector.of(userService::getByUsername, null).ret());
//        System.out.println("Protector 2: " + Protector.of(userService::getByUsername, "aa").ret());
//        System.out.println("Protector 3: " + Protector.of(userService::getByUsername, "w").ret());
//        System.out.println("Protector 1.1: " + Protector.of(userService::getByUsername, null).ret().orElseGet(Optional::empty));
//        System.out.println("Protector 2.1: " + Protector.of(userService::getByUsername, "aa").ret().orElseGet(Optional::empty));
//        System.out.println("Protector 3.1: " + Protector.of(userService::getByUsername, "w").ret().orElseGet(Optional::empty));
//
//        Protector<Optional<User>> p1 = Protector.of(userService::getByUsername, "w");
//        System.out.println("returnObject: " + p1.returnObject);
//        System.out.println("exceptionObject: " + p1.exceptionObject);
//
//        Optional<User> userFromServer1 = Protector.of(userService::getByUsername, "aa").ret().orElseGet(Optional::empty);
//        System.out.println("Protector 4: " + userFromServer1);

        System.out.println("==============");

//        User user1 = new User(0, "w", "123");
//        User user2 = new User(0, "q3", "123");
//        System.out.println("Protector 4: " + Protector.of(userService::insert, user1).ret());
//        System.out.println("Protector 5: " + Protector.of(userService::insert, user2).ret());
//        System.out.println("Protector 6: " + Protector.of(userService::insert, user1).ret().orElseGet(Optional::empty));
//        System.out.println("Protector 7: " + Protector.of(userService::insert, user2).ret().orElseGet(Optional::empty));

//        long id = Protector.of(userService::insert, user1).ret().orElse(0L);
//        long id2 = Protector.of(userService::insert, user2).ret().orElse(0L);
//        System.out.println("id: " + id);
//        System.out.println("id2: " + id2);

//        Protector<Long> p = Protector.of(userService::insert, user1);
//        System.out.println("returnObject: " + p.returnObject);
//        System.out.println("exceptionObject: " + p.exceptionObject);

//        long id3 = Protector.of(userService::insert, user2)
//                .onSuccess(r -> {
//                    System.out.println("Insert thành công! " + r);
//                })
//                .onError(e -> {
//                    System.out.println("Insert thất bại! " + e);
//                }).ret().orElse(0L);
//        System.out.println("id sau insert: " + id3);
//
//        System.out.println("Sau kiểm tra Protector");

        System.out.println("Kiểm tra getAll với Protector");

//        List<User> list1 = Protector.of(userService::getAll).get(ArrayList::new);
//        System.out.println("getAll list1: " + list1);
//
//        System.out.println("Kiểm tra delete với Protector");
//
//        Protector.of(userService::delete, 64L).onSuccess(r -> {
//            System.out.println("delete thành công: " + r);
//        }).onError(e -> {
//            System.out.println("delete thất bại: " + e);
//        });

        // Test Validator exist
        List<String> list2 = Validator.of(" ").isNotExistent(false, "ABC").toList();
        System.out.println("list2: " + list2);

        // onSuccessAndThen
//        String optionalUser = Protector.of(userService::insert, user1) // return ra Protector1 chứa user có id = 1

        // return ra Protector1 chứa user có id = 1
        // return ra Protector2 chứa user có uname=abc
//        String optionalUser = Protector.of(userService::getById, 47L)
//        Protector<Optional<User>> optionalUser = Protector.of(userService::getById, 48L)
//                .then(r -> {
//                    if (r.isPresent()) {
//                        return Protector.of(userService::getByUsername, "abc");
//                    }
//                    return Protector.error(new NoSuchElementException());
//                })
//                .then(r -> {
//                    if (r.isPresent()) {
//                        return Protector.of(userService::getByUsername, "wq55");
//                    }
//                    return Protector.error();
//                })
//                .fail(e -> System.out.println("có lỗi ở chuỗi protector này: " + e));
//        System.out.println("optionalUser: " + optionalUser);

//        String optionalUser1 = Protector.of(userService::getById, 100L)
//                .toString();
////                .ret().orElseGet(Optional::empty);
//        System.out.println("optionalUser1: " + optionalUser1);

//        Protector.of(userService::getById, 1L)
//                .then(r -> {
//                    System.out.println(r);
//                    return Protector.of(userService::getByUsername, "A");
//                })
//                .done(r -> System.out.println("Hai lệnh thực hiện thành công! " + r))
//                .fail(e -> System.out.println("Gặp lỗi truy vấn!"));

//        Protector.of(userService::getById, 1L)
//                .then(r -> Protector.of(userService::getByUsername, "A"))
//                .done(r -> System.out.println("Hai lệnh thực hiện thành công! " + r))
//                .fail(e -> System.out.println("Gặp lỗi truy vấn!"));

//        System.out.println("Protector mới");
//        Protector<Optional<User>> p1 = Protector.of(() -> userService.getByUsername("wkk"));
//        System.out.println(p1.get(Optional::empty));
//        Protector<List<User>> p2 = Protector.of(userService::getAll);
//        p2.get(ArrayList::new).forEach(System.out::println);
//
////        User u1 = new User(73L, "abc", "123");
////        Protector<Boolean> p3 = Protector.of(() -> userService.update(u1));
////        System.out.println(p3.get(false));
//
//        System.out.println("Chaining trong Protector mới");
//        Protector.of(() -> userService.getById(1L))
//                .then(r -> Protector.of(() -> userService.getByUsername("abc")))
//                .done(r -> System.out.println("Hai lệnh thực hiện thành công! " + r))
//                .fail(e -> System.out.println("Gặp lỗi truy vấn!"));
//
//        List<User> allUsers = Protector.of(userService::getAll)
//                .get(ArrayList::new);
//        System.out.println(allUsers);

        System.out.println("==============================================");

        CategoryService categoryService = new CategoryService();
//        System.out.println(categoryService.getAll());
//        System.out.println(categoryService.getPart(2, 1));
//
        ProductService productService = new ProductService();
        System.out.println("+++++++++++++++++++++++++");
//        productService.getOrderedPart(3, 0, "createdAt", "DESC").forEach(System.out::println);

        System.out.println("categoryService.getByProductId(1)");
        System.out.println(categoryService.getByProductId(1));

        ProductReviewService productReviewService = new ProductReviewService();
        System.out.println("productReviewService.getOrderedPartByProductId()");
        System.out.println(productReviewService.getOrderedPartByProductId(2, 0, "createdAt", "DESC", 3));
        System.out.println("productReviewService.countByProductId()");
        System.out.println(productReviewService.countByProductId(3));

        System.out.println("productService.getRandomPartByCategoryId()");
        productService.getRandomPartByCategoryId(4, 0, 1).forEach(System.out::println);


    }

    public static String test(String... s) {
        return Arrays.toString(s);
    }
}
